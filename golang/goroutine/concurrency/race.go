package main

import (
	"fmt"
	"sync"
	"runtime"
)

var (
	count int32
	wg sync.WaitGroup
)

func main() {
	wg.Add(2)
	go incr()
	go incr()
	wg.Wait()

	fmt.Println("count=", count)
}

// 自增，产生竞争状态
func incr() {
	defer wg.Done()

	for i := 0; i < 2; i++ {
		value := count
		runtime.Gosched()
		value++
		count = value
	}
}
