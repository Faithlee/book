package main

import (
	"fmt"
	//"time"
)

func main() {
	fmt.Println("通过channel发送数据")

	ch := make(chan int)
	// 没有接收方：fatal error: all goroutines are asleep - deadlock! 
	//ch<-0

	go func() {
		fmt.Println("goroutine start")

		ch <- 0

		fmt.Println("goroutine exit")
	}()
	go func () {
		fmt.Println("it's me")
		// 不会宕机?
		//<-ch
		//<-ch
		//<-ch
	}()

	//time.Sleep(time.Second)
	fmt.Println("wait goroutine")
	<-ch

	// 宕机？
	//<-ch
	//<-ch
	//<-ch

	fmt.Println("all done!")
}
