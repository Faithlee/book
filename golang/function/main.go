package main

import "fmt"
import "math"
import "flag"

func main(){
	fmt.Println("计算平方和:", sqrt(3,4))

	fmt.Println("函数声明的几种方法:")
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

	fmt.Println("匿名函数：")
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
