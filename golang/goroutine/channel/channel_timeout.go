package main

import (
	"fmt"
	"time"
)

func main() {
	ch := make(chan int)
	quit := make(chan bool)

	go func() {
		for {
			select {
				case num := <-ch:
					fmt.Printf("num=%d\n", num)
				case <-time.After(3 * time.Second):
					quit<-true
			}
		}
	}()

	for i := 0; i < 5; i++ {
		ch<-i
		time.Sleep(time.Second)
	}

	<-quit
}
