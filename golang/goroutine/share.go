package main

import (
	"fmt"
	"sync"
	"runtime"
)

var counter int

func count(i int, lock *sync.Mutex) {
	lock.Lock()
	counter++
	lock.Unlock()
	fmt.Printf("%d. counter: %d\n", i, counter)
}

func main() {
	lock := &sync.Mutex{}
	for i := 0; i < 10; i++ {
		go count(i, lock)
	}

	for {
		lock.Lock()
		c := counter
		lock.Unlock()
		// 让出cpu？todo 前后增加输出和协程执行对比？
		runtime.Gosched()

		if c >= 10 {
			break
		}
	}
}
