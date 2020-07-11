package main

import "fmt"

func main() {
	func(){
		sum := 0
		for i := 0; i < 1e6; i++ {
			sum += i
		}
		fmt.Println(sum)
	}()

	for i := 0; i < 4; i++ {
		g := func(i int) {fmt.Print("%d ", i)}
		g(i)
		fmt.Printf(" - g is of type %T and has value %v\n", g, g)
	}


	// defer改变命名返回值：2
	fmt.Println(f())
}

func f() (ret int) {
	defer func() {
		ret++
	}()
	return 1
}
