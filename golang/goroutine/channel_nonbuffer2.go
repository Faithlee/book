package main

import (
	"fmt"
	"time"
	"sync"
)

var wg sync.WaitGroup

func main() {
	baton := make(chan int)

	wg.Add(1)

	go Runner(baton)

	baton<-1

	wg.Wait()
}

func Runner(baton chan int) {
	var newRunner int

	// 等待拿到接力棒
	runner := <-baton
	// 开始跑 
	fmt.Printf("Runner %d running with baton\n", runner)
	time.Sleep(1 * time.Second)

	if runner != 4 {
		newRunner = runner+1
		fmt.Printf("Runner %d to the line\n", newRunner)
		go Runner(baton)
	}

	if runner == 4 {
		fmt.Printf("Runner %d finished and Race over\n", runner)
		wg.Done()
		return
	}

	fmt.Printf("Runer %d exchange with Runner %d\n", runner, newRunner)

	baton<-newRunner
}
