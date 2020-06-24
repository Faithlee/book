package main

import "fmt"
import "sync"

var (
	count int
	lock1 sync.Mutex
	lock2 sync.RWMutex
)

func main() {
	setCount(1)

	fmt.Println(getCount())
}

func setCount(i int) {
	lock1.Lock()
	// defer lock1.Unlock()
	count = i
	lock1.Unlock()
}

func getCount() int {
	lock1.Lock()
	defer lock1.Unlock()

	return count
}
