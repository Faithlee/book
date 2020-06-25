package main

import (
	"fmt"
	"time"
)

var ch chan int

// for range循环channel
func main() {
	ch = make(chan int)

	go send()

	for data := range ch {
		fmt.Println(data)

		if data == 0 {
			break
		}
	}
}

func send() {
	for i := 3; i >= 0; i-- {
		ch<-i

		time.Sleep(time.Second)
	}
}
