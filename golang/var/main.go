package main

import "fmt"
import "math"
import "flag"
import "time"

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
	fmt.Println()

	// 指针
	var cat int = 1
	var fruit string = "banana"
	fmt.Printf("%p %p\n", &cat, &fruit)
	// 从指针获取指针指向的值
	var locate = "point 10090, 90000"
	ptr := &locate
	// 打印类型及地址
	fmt.Printf("ptr type: %T, ptr address: %p\n", ptr, ptr)
	// 对指针取值操作
	value := *ptr
	fmt.Printf("value type: %T, value: %s\n", value, value)
	// 指针修改值
	num1, num2 := 1,2
	swap(&num1, &num2)
	fmt.Printf("num1=%d, num2=%d\n", num1, num2)

	num3, num4 := 3,4
	fmt.Printf("num3=%p, num4=%p\n", &num3, &num4)
	fmt.Printf("num3=%d, num4=%d\n", num3, num4)
	swap2(&num3, &num4)
	fmt.Printf("num3=%p, num4=%p\n", &num3, &num4)
	fmt.Printf("num3=%d, num4=%d\n", num3, num4)

	// 使用flag处理命令行参数
	var mode = flag.String("mode", "", "process mode")
	flag.Parse()
	fmt.Println("flag命令行mode:", *mode)
	pointer()

	// 常量
	constant()

	// 类型别名
	typeAlias()
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

func swap(a, b *int){
	t := *a
	*a = *b
	*b = t
}

func swap2(a, b *int) {
	a, b = b, a
}

func pointer() {
	// 创建指针的方法
	str := new(string)
	*str = "go语言编程"
	fmt.Println("创建指针的方法:", *str)
}

func constant(){
	const pi = 3.14159
	const s string = "str"
	const c1 = 'c'
	const c2 = '\x41'
	const c3 = '5'
	fmt.Printf("常量定义: %f, %s, %c, %c, %c\n\n", pi, s, c1, c2, c3)

	// 在编译时能够确定下来
	const f1 = 2/3
	const f2 = 5/3
	fmt.Println("常量可以使用赋值运算:", f1, f2)

	// 批量定义1
	const (
		e = 2.7182828
		PI = 3.141596
	)
	fmt.Printf("批量声明: %f, %f\n", e, PI)

	// 批量定义2
	const (
		a = 1
		b
		c = 2
		d
	)
	fmt.Printf("批量声明: %d, %d, %d, %d\n\n", a, b, c, d)

	// todo 打印常量的类型？
	// 常量声明可以包含一个类型和一个值
	const noDelay time.Duration = 1;
	const timeout = 5 * time.Minute
	fmt.Printf("%T %[1]v\n", noDelay)
	fmt.Printf("%T %[1]v\n", timeout)
	fmt.Printf("%T %[1]v\n\n", time.Minute)

	// iota常量生成器
	type Weekday int
	const (
		Sunday Weekday = iota
		Monday
		Tuesday
		Wednesday
		Thursday
		Friday
		Saturday
	)
	fmt.Println("iota实现星期:", Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday)

	// 无类型常量：布尔、整数、浮点型、字符、字符串、复数
	// 通过延迟确定常量的类型，提供更高的运算精度，不需要显式的转换
	//var x float32 = math.Pi
	//var y float64 = math.Pi
	//var z complex128 = math.Pi
	fmt.Println()
}

// todo 在结构体成员嵌入时使用别名？
func typeAlias(){
	// 类型定义
	type NewInt int

	var a NewInt
	fmt.Printf("a type: %T\n", a)

	// 类型别名
	type IntAlias = int
	var b IntAlias
	fmt.Printf("b type: %T\n", b)
}
