package main

import "fmt"

func main() {
	fmt.Println("四.流程控制")

	ifBranch()
	forStructure()
	nine()
	forRange()
	switchCase()
	gotoReturnLoop()
	breakFlow()
	continueFlow()
}

func ifBranch() {
	fmt.Println("1.if else分支语句")
	var ten int = 5
	if ten > 10 {
		fmt.Println(ten, "大于10")
	} else {
		fmt.Println(ten, "小于等于10")
	}

	// if表达式中可以添加执行语句
	/*
	if err := Connect(); err!=nil {
		fmt.Println(err)
		return
	}
	*/
}

func forStructure() {
	fmt.Println("2.for循环结构:")

	// 写法1
	sum1 := 0
	for i:=0; i<10; i++ {
		sum1+=i
	}
	fmt.Println("sum1=", sum1)

	// 写法2：无限循环
	sum2 := 0
	for {
		sum2++
		if sum2 > 100 {
			break
		}
	}
	fmt.Println("sum2=", sum2)

	// 写法3，使用break中断哪层循环
	for j:=0; j<5; j++ {
		for i:=0; i<10; i++ {
			if i > 5 {
				//break JLoop
			}
			//fmt.Println(i)
		}
	}
	//JLoop:
	//fmt.Println("break loop")

	// 写法4：初始语句放在外层，但分号是必须的
	var step int = 2
	for ; step > 0; step-- {
		fmt.Println("step=", step)
	}

	// 写法5，将条件语句放在循环体中
	var i int
	for	; ; i++ {
		if i > 10 {
			break
		}
		fmt.Printf("%d ", i);
	}
	fmt.Println()

	// 写法6：只有一个循环条件的循环
	i = 0
	for i <= 10 {
		fmt.Printf("%d ", i)
		i++
	}
	fmt.Println("\n")
}

func nine() {
	fmt.Println("3.九九乘法表:")
	for x:=1; x<=9; x++ {
		for y:=1; y<=x; y++ {
			fmt.Printf("%d*%d=%d ", x, y, x*y)
		}
		fmt.Println()
	}
}

func forRange() {
	fmt.Println("4.for range循环")

	// 遍历数组、切片
	for key, val := range []int{1,2,3,4}{
		fmt.Printf("遍历切片: key:%d, val:%d\n", key, val)
	}

	// 遍历字符串
	var str = "hello 你好"
	for key, val := range str {
		fmt.Printf("遍历字符串: key:%d, val:0x%x\n", key, val)
	}

	m := map[string]int{
		"hello": 100,
		"world": 200,
	}
	for key, val := range m {
		fmt.Printf("遍历map, key:%s, value:%d\n", key, val)
	}

	// 遍历通道
	c := make(chan int)
	go func(){
		c<-1
		c<-2
		c<-3
		close(c)
	}()
	for v := range c {
		fmt.Printf("遍历channel: %d\n", v)
	}

	// 可以使用_匿名变量处理遍历时不需要的变量，也不会分配空间
	fmt.Println()
}

func switchCase(){
	fmt.Println("5.swicth case分支:")
	// case代码块是独立的
	var a = "hello"
	switch a {
		case "hello":
			fmt.Println("Hello")
		case "world":
			fmt.Println("World")
		default:
			fmt.Println("default")
	}

	
	// 一分支多个值
	var str = "mum"
	switch str {
		case "mum","dad":
			fmt.Println("family")
		default:
			fmt.Println("default")
	}

	fmt.Print("分支表达式:")
	var i int = 110
	switch {
		case i > 10 && i < 100:
			fmt.Println(i)
		case i > 100 && i < 200:
			fmt.Println(i+100)
	}

	fmt.Print("跨越case的fallthrough: ")
	var s = "hello"
	switch {
		case s == "hello":
			fmt.Print("Hello ")
			fallthrough
		case s != "world":
			fmt.Print("World")
	}

	fmt.Println("\n")
}

func gotoReturnLoop() {
	fmt.Println("6.goto退出多层:")
	var breakAgain bool
	for x := 0; x < 10; x++ {
		for y := 0; y < 10; y++ {
			if y == 2 {
				breakAgain = true
				break
			}
		}
		if breakAgain {
			break
		}
	}
	fmt.Println("传统退出多层:break")

	for x := 0; x < 10; x++ {
		for y := 0; y < 10; y++ {
			if y == 2 {
				goto breakHere
			}
		}
	}
breakHere:
	fmt.Println("goto退出多层:goto")

	// 使用goto处理错误，防止传统处理错误时重复的代码修改问题
	/*
	err := firstError()
	if err != nil {
		goto onExit
	}
	err := sencodError()
	if err != nil {
		goto onExit
	}
onExit:
	fmt.Println(err)
	exitProcess()
	*/
	fmt.Println()
}

func breakFlow() {
	fmt.Printf("7.break中断for/switch/select代码块: ")

OuterLoop:
	for i := 0; i < 2; i++ {
		for j := 0; j < 5; j++ {
			switch j {
				case 2:
					fmt.Println(i, j)
					break OuterLoop
				case 3:
					fmt.Println(i, j)
					break OuterLoop
			}
		}
	}

	fmt.Println("break label OuterLoop!")
	fmt.Println()
}

func continueFlow() {
	fmt.Printf("8.continue中断当前循环: ")

OuterLoop:
	for x := 0; x < 10; x++ {
		for y := 0; y < 10; y++ {
			switch y {
				case 2:
					fmt.Println(x, y)
					continue OuterLoop
			}
		}
	}

	fmt.Println()
}

