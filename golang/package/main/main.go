package main

// 标准引入格式
//import "fmt"
// 自定义别名引用格式 
import F "fmt"


import "package/demo"
// 互斥锁
//import "package/count"
import "package/timebase"
import "package/regexpbase"
import "package/bigbase"
import "package/osbase"
import "package/flagbase"

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
	F.Println("3.时间包")
	timebase.CurrentTime()
	timebase.TimeStamp()
	timebase.Operate()
	// todo 定时器
	//timebase.Ticker()
	timebase.TimeFormat()
	timebase.ParseTime()
	F.Println()

	F.Println("4.正则表达式")
	regexpbase.MatchStr()
	regexpbase.MatchFloat()
	regexpbase.MatchWebTag()
	regexpbase.MatchAndReplace()

	F.Println("5.超出int64的大数计算:")
	bigbase.Uint64()
	bigbase.CalculateFibonacci()

	F.Println("6.os包")
	osbase.Base()
	osbase.Exec()
	osbase.User()
	//osbase.Signal()

	F.Println("7.命令行参数解析")
	//flagbase.BaseType()
	//flagbase.BaseTypeVar()
	//flagbase.Args()
	flagbase.Define()
}
