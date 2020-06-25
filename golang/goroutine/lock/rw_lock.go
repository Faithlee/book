package main

import "fmt"
import "sync"

var (
	count int
	lock sync.RWMutex
)

func main() {
	SetCount(1)

	fmt.Println("RWMutex count=", GetCount())
}

func SetCount(i int) {
	lock.RLock()
	count = i
	lock.RUnlock()
}

func GetCount() int {
	lock.RLock()
	defer lock.RUnlock()

	return count
}
