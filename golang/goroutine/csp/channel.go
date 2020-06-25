package main

import (
	"fmt"
)

func main() {
	done := make(chan int)

	go func() {
		fmt.Println("hello, golang!")
		done<-1
	}()

	<-done
}
