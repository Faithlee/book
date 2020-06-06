package main

import "fmt"
import "math"

var p float32 = 3.14

func main(){
	n:=100
	a,s:=1,"abc"
	fmt.Println("n=",n)
	fmt.Println("a=", a)
	fmt.Println("s=", s)

	var attack=40
	var defence=20
	var damageRate float32 = 0.17
	var damage = float32(attack-defence)*damageRate
	fmt.Println(damage)

	var a1 int=100
	var b1 int =200
	var t int
	t=a1
	a1=b1
	b1=t
	fmt.Println(a1,b1)

	var a2 int =100
	var b2 int = 200
	a2 = a2^b2
	b2 = b2^a2
	a2 = a2^b2
	fmt.Println(a2,b2)

	var a3 int = 100
	var b3 int = 200
	a3,b3 = b3,a3
	fmt.Println(a3, b3)

	a4,_ := GetData()
	_, b4 := GetData()
	fmt.Println(a4,b4)

	var a5 int = 3
	var b5 int = 4
	c := a5+b5
	fmt.Printf("a5=%d, b5=%d, c=%d\n", a5, b5, c)
	fmt.Println()

	// 全局变量：优先使用局部变量
	fmt.Printf("p=%f\n", p)
	var p int = 3
	fmt.Printf("p=%d\n", p)

	// 形式参数
	var x int = 3
	var y int = 4
	fmt.Printf("main函数x=%d, y=%d\n", x, y)
	z := sum(x,y)
	fmt.Printf("main函数z=%d\n", z)
	fmt.Println()

	// 浮点数: math.MaxFloat32
	// 浮点数: math.MaxFloat64
	const e = .71828
	fmt.Printf("e=%f\n", e)
	fmt.Printf("pi=%f\n", math.Pi)
	fmt.Printf("pi=%.2f\n", math.Pi)

	// 布尔型
	var d int = 3
	fmt.Println("int d to bool:", itob(d))

	// 字符串
	var str = "hello, go!"
	fmt.Println(str)
	fmt.Println("str len is", len(str))
	// 字符串拼接
	str1 := "hello," + "world!"
	fmt.Println(str1)
	// 定义多行字符串
	const text = `
hello,world!
hello,go!
`
	fmt.Print(text)
	// 字符
	var ch1 byte = 65
	var ch2 byte = '\x41'
	fmt.Printf("ch1=%c, ch2=%c\n", ch1, ch2)

	// 类型转换
	fmt.Println("int8 range:", math.MinInt8, math.MaxInt8)
	fmt.Println("int16 range:", math.MinInt16, math.MaxInt16)

	var i32 int32 = 1047483647
	fmt.Printf("int32: 0x%x %d\n", i32, i32)
	// 将32位转换为16位会截断数值
	i16 := int16(i32)
	fmt.Printf("int16: 0x%x %d\n", i16, i16)
	// 将浮点数转换为整型，精度丢失
	fmt.Println(int(p));


}

func GetData()(int, int){
	return 100,200
}

func sum(x, y int) int {
	fmt.Printf("sum函数x=%d, y=%d\n", x, y)
	num := x+y
	return num
}

func btoi(b bool)int {
	if (b) {
		return 1
	}

	return 0;
}

func itob(i int) bool {
	return i != 0
}
