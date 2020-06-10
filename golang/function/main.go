package main

import "fmt"
import "math"

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
	fmt.Println("fire!!!")
}
