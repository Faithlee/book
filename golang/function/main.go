package main

import "fmt"
import "math"
import "flag"
import "bytes"

func main(){
	fmt.Println("计算平方和:", sqrt(3,4))

	fmt.Println("1.函数声明的几种方法:")
	fmt.Printf("%T\n", add)
	fmt.Printf("%T\n", sub)
	fmt.Printf("%T\n", first)
	fmt.Printf("%T\n\n", zero)

	fmt.Println("返回值是一组相同类型:")
	a1,b1 := returnTwo()
	fmt.Printf("a=%d, b=%d\n", a1, b1)
	a2, b2 := returnTwo2()
	fmt.Printf("a=%d, b=%d\n", a2, b2)
	a3, b3 := returnTwo3()
	fmt.Printf("a=%d, b=%d\n", a3, b3)
	fmt.Println()

	fmt.Println("将函数作为值保存在变量中：")
	var f func()
	f = fire
	f()

	fmt.Println("2.匿名函数：")
	func (data int) {
		fmt.Println("hello", data)
	}(100)

	fmt.Println("匿名函数赋值给变量:")
	f1 := func (data int) {
		fmt.Println("hello", data)
	}
	f1(100)
	fmt.Println()

	// 匿名函数用作回调函数
	visit([]int{1,2,3,4}, func(v int) {
		fmt.Println("回调函数", v)
	})

	// 匿名函数封装操作
	var skillParam = flag.String("skill", "", "skill to perform")
	flag.Parse()

	var skill = map[string]func(){
		"fire": func() {fmt.Println("chicken fire\n")},
		"run": func(){fmt.Println("soldier run\n")},
		"fly": func(){fmt.Println("angel fly\n")},
	}
	if f, ok := skill[*skillParam]; ok {
		f()
	} else {
		fmt.Println("skill not found\n")
	}

	// 闭包内部修改引用的变量，会对变量进行实际的修改 
	fmt.Println("3.闭包内部修改引用的变量:")
	str := "hello,world"
	func1 := func() {
		str = "hello,golang"
	}
	func1()
	fmt.Println(str)

	fmt.Println("闭包的记忆效应:")
	accumulator := accumulate(1)
	fmt.Println("累加器:", accumulator())
	fmt.Printf("%p\n", &accumulator)
	fmt.Println("累加器:", accumulator())
	fmt.Printf("%p\n", &accumulator)

	accumulator2 := accumulate(10)
	fmt.Println("累加器2:", accumulator2())
	fmt.Printf("%p\n", &accumulator2)
	fmt.Println("累加器2:", accumulator2())
	fmt.Printf("%p\n\n", &accumulator2)

	generator := generator("high")
	name,hp := generator()
	fmt.Println("记忆效应:", name, hp)
	fmt.Println()

	fmt.Println("4.可变参数函数:")
	variableFunc(1,2,3,4)
	variableFunc2(1, 234, "hello", 3.14)
	fmt.Println("合并字符串:", joinStrings("apple", " banana", " orange"))

	// 打印可变参数的值与类型
	ret := printType(100, true, "golang")
	fmt.Println("打印可变参数的值与类型:", ret)

	fmt.Println("多个可变函数中传递参数：")
	print(1, 3.14, "golang")
}

func sqrt(x, y float64) float64 {
	return math.Sqrt(x*x + y*y)
}

// 函数声明的几种方法:
func add(x int, y int) int {
	return x + y
}
func sub(x, y int) (z int) {
	z = x - y
	return
}
func first(x int, _ int) int {
	return x
}
func zero(int, int) int {
	return 0
}

// 返回值是一组相同类型
func returnTwo() (int, int) {
	return 1, 2
}
func returnTwo2() (a, b int) {
	return 3,4
}
func returnTwo3() (a, b int) {
	a = 5
	return a, 6 
}


//将函数作为值保存在变量中
func fire() {
	fmt.Println("fire!!!\n")
}

// 回调函数
func visit(list []int, f func(int)) {
	for _, v := range list {
		f(v)
	}
}

// 记忆效应
func accumulate(value int) func() int {
	return func() int {
		value++
		return value
	}
}

func generator(name string) func()(string, int) {
	hp := 100
	return func()(string, int) {
		return name,hp
	}
}

// 可变参数函数
func variableFunc(args ...int) {
	for _, arg := range args {
		fmt.Println(arg)
	}
}
// 任意类型的可变参数
func variableFunc2(args ...interface{}) {
	for _, arg := range args {
		switch arg.(type) {
			case int:
				fmt.Println(arg, "is an int value")
			case string:
				fmt.Println(arg, "is a string value")
			case int64:
				fmt.Println(arg, "is an int64 value")
			default:
				fmt.Println("unknown type")
		}
	}
}

// join
func joinStrings(slist ...string) string {
	var b bytes.Buffer
	for _,str := range slist {
		b.WriteString(str)
	}

	return b.String()
}

func printType(slist ...interface{}) string {
	var b bytes.Buffer
	for _, s := range slist {
		str := fmt.Sprintf("%v", s)
		var typeStr string
		switch s.(type) {
			case bool:
				typeStr = "bool"
			case string:
				typeStr = "string"
			case int:
				typeStr = "int"
		}

		b.WriteString("value:")
		b.WriteString(str)
		b.WriteString(" type:")
		b.WriteString(typeStr)
		b.WriteString("\n")
	}

	return b.String()
}

// 多个可变函数传递参数
func print(slist ...interface{}) {
	// 可变参数时可以省略类型
	rawPrint(slist...)
	// slist当作一个整体传
	rawPrint(slist)
}
func rawPrint(slist ...interface{}) {
	for _, str := range slist {
		fmt.Println(str)
	}
}
