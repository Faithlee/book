package timebase

import (
	"fmt"
	"time"
)

func CurrentTime() {
	fmt.Println("获取时间:")
	now := time.Now()
	fmt.Printf("current time: %v\n", now)

	year := now.Year()
	month := now.Month()
	day := now.Day()
	hour := now.Hour()
	minute := now.Minute()
	second := now.Second()
	fmt.Printf("%d-%02d-%02d %02d:%02d:%02d\n", year, month, day, hour, minute, second)

	// 获取星期几
	weekday := now.Weekday()
	fmt.Println("当前星期几: ", weekday.String())
	fmt.Println()
}

// 时间戳
func TimeStamp() {
	fmt.Println("时间戳:")

	// 获取时间戳
	now := time.Now()
	timestamp1 := now.Unix()
	timestamp2 := now.UnixNano()

	fmt.Printf("时间戳1: %v\n", timestamp1)
	fmt.Printf("时间戳2: %v\n", timestamp2)

	// 时间戳转为时间
	timeObj := time.Unix(timestamp1, 0)
	fmt.Println("时间戳转为时间:", timeObj)
	fmt.Println()
}

// 时间计算操作
func Operate() {
	fmt.Println("时间计算操作:")
	now := time.Now()
	later := now.Add(time.Hour)
	fmt.Printf("当前时间: %v\n累加1h后: %v\n", now, later)

	// sub: 时间差
	fmt.Println()
}

// 定时器
func Ticker() {
	// 1秒间隔的定时器
	fmt.Println("1秒的定时器:")
	ticker := time.Tick(time.Second)
	for i := range ticker {
		fmt.Println(i)
	}
	fmt.Println()
}

func TimeFormat() {
	fmt.Println("格式时间:")

	now := time.Now()
	//24小时制
	fmt.Println(now.Format("2006-01-02 15:04:05.000 Mon Jan"))
	fmt.Println(now.Format("2006-01-02 03:04:05.000 PM Mon Jan"))
	fmt.Println(now.Format("2006/01/02 15:04"))
	fmt.Println(now.Format("15:04 2006/01/02"))
	fmt.Println(now.Format("2006/01/02"))

	fmt.Println()
}

func ParseTime() {
	fmt.Println("解析时间:")

	layout := "2006-01-02 15:04:05"
	datetime := "2020-06-20 19:44:50"

	// 注意Parse与ParseInLocation的区别
	timeObj1, _ := time.Parse(layout, datetime)
	fmt.Println(timeObj1)

	timeObj2, _ := time.ParseInLocation(layout, datetime, time.Local)
	fmt.Println(timeObj2)
}
