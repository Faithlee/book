package main

import (
	"fmt"
	"sync"
)


func main() {
	var lock sync.Mutex

	lock.Lock()
	go func() {
		fmt.Println("hello, golang!")
		lock.Unlock()
	}()

	lock.Lock()
}
