package main

import "fmt"
import "time"

func main() {
	fmt.Println(time.Now())

	// 并发执行程序，普通函数
	//go running()
	// 并发执行程序，匿名函数
	go func() {
		var times int
		for {
			times++
			fmt.Println("tick", times)

			time.Sleep(time.Second)
		}
	}()

	var input string
	fmt.Scanln(&input)
}


func running() {
	var times int
	for {
		times++
		fmt.Println("tick", times)

		time.Sleep(time.Second)
	}
}

