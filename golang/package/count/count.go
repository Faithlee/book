package count

import (
	"fmt"
	"time"
	"sync"
)

func Run() {
	var a = 0
	for i := 0; i < 1000; i++ {
		go func (idx int) {
			a += 1
			fmt.Println(a)
		}(i)
	}

	time.Sleep(time.Second)
}

func SyncRun() {
	var a = 0

	var lock sync.Mutex
	for i := 0; i < 1000; i++ {
		go func(idx int) {
			lock.Lock()
			defer lock.Unlock()
			a += 1
			fmt.Printf("goroutine %d, a=%d\n", idx, a)
		}(i)
	}

	// 确保所有协程结束
	time.Sleep(time.Second)
}
