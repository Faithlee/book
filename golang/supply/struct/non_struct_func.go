package main

import "fmt"


func main() {
	fmt.Println("非结构体类型方法[]int: ", IntVector{1,2,3}.Sum())
}

type IntVector []int

func (v IntVector) Sum() (s int){
	for _, v := range v {
		s += v
	}

	return
}
