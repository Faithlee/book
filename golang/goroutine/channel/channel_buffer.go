package main

import "fmt"

func main() {
	ch := make(chan int, 3)

	// 对比channel的缓冲长度与容量
	fmt.Println("len of chan:", len(ch))
	fmt.Println("cap of chan:", cap(ch))

	// 使用缓冲channel发送数据没有超出长度时不会阻塞
	ch<-1
	ch<-2
	ch<-3

	fmt.Println("len of chan:", len(ch))
	fmt.Println("cap of chan:", cap(ch))
}
