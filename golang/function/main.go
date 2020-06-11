package main

import "fmt"
import "math"
import "flag"
import "bytes"
import "os"
import "sync"

func main(){
	fmt.Println("sync.Mutex计算平方和:", sqrt(3,4))

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

	fmt.Println("5.defer延迟执行语句:")
	fmt.Println("defer begin")
	defer fmt.Println("defer 1")
	defer fmt.Println("defer 2")
	defer fmt.Println("defer 3")
	fmt.Println("defer end")

	filesize := filesize("./src/function/main.go")
	fmt.Println("filesize: ", filesize)

	fmt.Println("6.递归调用:")
	// fibonacci
	for i := 1; i <= 10; i++ {
		fmt.Printf("fibonacci(%d)=%d\n", i, fibonacci(i))
	}
	// 阶乘
	for i := 1; i < 10; i++ {
		fmt.Printf("factorial(%d)=%d\n", i, factorial(uint64(i)))
	}
	// 多个函数递归调用
	fmt.Printf("%d is even, is %t\n", 15, even(15))
	fmt.Printf("%d is even, is %t\n", 16, even(16))
	fmt.Printf("%d is odd, is %t\n", 17, odd(17))
	fmt.Printf("%d is odd, is %t\n", 18, odd(18))
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

// defer 退出时释放资源
var (
	valueByKey = make(map[string]int)
	valueByKeyGuard sync.Mutex
)
// 传统加锁处理方式
func readValue(key string) int {
	// 对共享资源加锁
	valueByKeyGuard.Lock()
	v := valueByKey[key]
	valueByKeyGuard.Unlock()

	return v
}
// 使用defer延迟释放资源
func readValueUseDefer(key string) int {
	valueByKeyGuard.Lock()
	// 不会马上执行，延迟到函数调用结束
	defer valueByKeyGuard.Unlock()

	return valueByKey[key]
}

// 延迟释放句柄
func filesize(filename string)int64 {
	f,err := os.Open(filename)
	if err != nil {
		return -1
	}
	// 延迟到return之前执行
	defer f.Close()

	info,err := f.Stat()
	if err != nil {
		// 触发defer
		return -1
	}

	size := info.Size()

	// 触发defer
	return size
}

// 递归函数1
func fibonacci(i int) (res int) {
	if i <= 2 {
		res = 1
	} else {
		res = fibonacci(i-1) + fibonacci(i-2)
	}

	return
}
// 递归函数2
func factorial(n uint64) (res uint64) {
	if n > 1 {
		res = factorial(n-1) * n
		return res
	}

	return 1
}

// 递归函数3：函数之间相互调用
func even(n int) bool {
	if n == 0 {
		return true
	}

	return odd(n - 1)
}
func odd(n int) bool {
	if n == 0 {
		return false
	}

	return even(n - 1)
}
