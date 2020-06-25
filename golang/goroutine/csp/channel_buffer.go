package main

import (
	"fmt"
)

func main() {
	ch := make(chan int, 10)

	for i := 0; i < cap(ch); i++ {
		go func(n int) {
			fmt.Println("id: ", n)
			ch<-i
		}(i)
	}

	for i := 0; i < cap(ch); i++ {
		<-ch
	}
}
