package main

import (
	"fmt"
	"sync"
	"runtime"
)

// 使用互斥锁访问共享资源
var (
	counter int64
	wg sync.WaitGroup
	lock sync.Mutex
)

func main() {
	wg.Add(2)

	go incr(1)
	go incr(2)

	wg.Wait()
	fmt.Println(counter)
}

func incr(id int) {
	defer wg.Done()

	for count := 0; count < 2; count++ {
		lock.Lock()
		{
			value := counter
			runtime.Gosched()
			value++
			counter = value
		}
		lock.Unlock()
	}
}

