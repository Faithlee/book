package main

import (
	"fmt"
	"time"
)

var ch chan int

// 经典生产消费模式
func main() {
	ch = make(chan int)

	go receive()

	for i := 1; i <= 10; i++ {
		ch<-i
		time.Sleep(time.Second)
	}

	// 通知结束receive
	ch<-0
	// 等待receive结束
	<-ch
}


func receive() {
	for {
		data := <-ch
		fmt.Println(data)

		if data == 0 {
			fmt.Println("退出循环")
			break
		}
	}

	ch<-0
}
