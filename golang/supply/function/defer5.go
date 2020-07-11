package main

import "fmt"
import "io"
import "log"

func main() {
	function1()
	fmt.Println()

	function3()
	fmt.Println()

	function4()
	fmt.Println("\n")

	b()
	fmt.Println("\n")

	logs("Go")
}

func function1() {
	fmt.Println("In function1 at the top")
	defer function2()
	fmt.Println("In function1 at the bottom")
}

func function2() {
	fmt.Println("function2: defered until the end of the calling function")
}

func function3() {
	i := 0
	defer fmt.Println(i)
	i++
}

// defer调用栈
func function4() {
	for i := 0; i < 5; i++ {
		defer fmt.Printf("%d ", i)
	}
}

// 追踪代码执行
func trace(s string) {
	fmt.Println("entering:", s)
}
func untrace(s string) {
	fmt.Println("leaving:", s)
}

func a () {
	trace("a")
	defer untrace("a")
	fmt.Println("in a")
}
func b () {
	trace("b")
	defer untrace("b")
	fmt.Println("b")
	a()
}

// 记录参数及返回值
func logs(s string) (n int, err error) {
	defer func() {
		log.Printf("func(%q) = %d, %v", s, n, err)
	}()

	return 7, io.EOF
}
