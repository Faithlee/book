package main

import (
	"fmt"
	"time"
	"sync"
	"runtime"
)

func main() {
	runtime.GOMAXPROCS(4)

	const runtime = 1*time.Second
	var sharedLock sync.Mutex
	var wg sync.WaitGroup

	greedyWorker := func() {
		defer wg.Done()
		var count int
		for begin := time.Now(); time.Since(begin) <= runtime; {
			sharedLock.Lock()
			time.Sleep(3 * time.Nanosecond)
			sharedLock.Unlock()
			count++
		}

		fmt.Printf("Greedy Worker execute %v work loops\n", count)
	}

	politeWorker := func(){
		defer wg.Done()
		var count int
		for begin := time.Now(); time.Since(begin) < runtime; {
			sharedLock.Lock()
			time.Sleep(1 * time.Nanosecond)
			sharedLock.Unlock()

			sharedLock.Lock()
			time.Sleep(1 * time.Nanosecond)
			sharedLock.Unlock()

			sharedLock.Lock()
			time.Sleep(1 * time.Nanosecond)
			sharedLock.Unlock()
			count++
		}
		fmt.Printf("Polite Worker execute %v work loops\n", count)
	}

	wg.Add(2)
	go greedyWorker()
	go politeWorker()
	wg.Wait()
}
