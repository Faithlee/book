package main

// 标准引入格式
//import "fmt"
// 自定义别名引用格式 
import F "fmt"


import "package/demo"
// 互斥锁
//import "package/count"
import "package/timebase"

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
	F.Println("sync.Mutex")
	// 不加锁
	//count.Run()
	// 加锁
	//count.SyncRun()
	F.Println()

	// time包
	F.Println("3.时间包:")
	timebase.CurrentTime()
	timebase.TimeStamp()
	timebase.Operate()
	// todo 定时器
	//timebase.Ticker()
	timebase.TimeFormat()
	timebase.ParseTime()
	F.Println()
}
