package main

import (
	"fmt"
	"time"
	"bytes"
	"sync"
	"sync/atomic"
	"runtime"
)

func main() {
	runtime.GOMAXPROCS(4)

	cv := sync.NewCond(&sync.Mutex{})
	go func () {
		for range time.Tick(1 * time.Second) {
			cv.Broadcast()
		}
	}()

	takeStep := func() {
		cv.L.Lock()
		cv.Wait()
		cv.L.Unlock()
	}

	tryDir := func(dirName string, dir *int32, out *bytes.Buffer) bool {
		fmt.Fprintf(out, " %+v", dirName)
		atomic.AddInt32(dir, 1)
		takeStep()
		if atomic.LoadInt32(dir) == 1 {
			fmt.Fprintf(out, ". Success!")
			return true
		}

		takeStep()
		atomic.AddInt32(dir, -1)
		return false
	}

	var left, right int32
	tryLeft := func (out *bytes.Buffer) bool {
		return tryDir("向左", &left, out)
	}
	tryRight := func (out *bytes.Buffer) bool {
		return tryDir("向右", &right, out)
	}

	walk := func(walking *sync.WaitGroup, name string) {
		var out bytes.Buffer
		defer walking.Done()
		defer func() {
			fmt.Println(out.String())
		}()
		fmt.Fprintf(&out, "%v is trying to scoot:", name)

		for i := 0; i < 5; i++ {
			if tryLeft(&out)||tryRight(&out) {
				return
			}
		}
		fmt.Fprintf(&out, "\n%v is tried!", name)
	}

	var trail sync.WaitGroup
	trail.Add(2)
	go walk(&trail, "Boy")
	go walk(&trail, "Girl")
	trail.Wait()
}
