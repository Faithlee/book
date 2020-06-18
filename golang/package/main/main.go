package main

// 标准引入格式
//import "fmt"
// 自定义别名引用格式 
import F "fmt"


import "package/demo"
import "package/count"

func main() {
	// 1	
	//fmt.Prinln("hello, golang!")

	// 2
	F.Println("自定义别名!")

	// 引入自定义包
	hero := &demo.Hero{
		Name: "关羽",
		Kind: 1,
	}
	hero.Attack()
	hero.Defend()

	F.Println("2.sync包")
	// 不加锁
	count.Run()
	// 加锁
	count.SyncRun()

}
