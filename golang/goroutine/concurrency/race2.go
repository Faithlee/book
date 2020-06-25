package main

import (
	"fmt"
	"sync"
	"sync/atomic"
	"time"
)

// 使用atom访问共享资源
func main() {
	wg.Add(2)
	go doWork("A")
	go doWork("B")

	time.Sleep(1 * time.Second)
	fmt.Println("shutdown now")
	atomic.StoreInt64(&shutdown, 1)
	wg.Wait()
}


var (
	shutdown int64
	wg sync.WaitGroup
)

func doWork(name string) {
	defer wg.Done()

	for {
		fmt.Printf("Doing %s Work\n", name)
		time.Sleep(250 * time.Millisecond)

		if atomic.LoadInt64(&shutdown) == 1 {
			fmt.Printf("shuting %s down\n", name)
			break
		}
	}
} 
