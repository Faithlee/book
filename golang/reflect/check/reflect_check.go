package main

import (
	"fmt"
	"reflect"
)

func main() {
	//i := make(map[int]string)
	//fmt.Println(i == nil)
	// isNil()只能对通道、函数、接口、map、指针或 切片使用

	fmt.Println("check Reflect Value isNil or isValid:")
	var a *int
	fmt.Println("  var a *int:", reflect.ValueOf(a).IsNil())
	fmt.Println("  (*int)(nil):", reflect.ValueOf((*int)(nil)).IsNil())
	fmt.Println()

	// nil值
	fmt.Println("  nil:", reflect.ValueOf(nil).IsValid())
	//fmt.Println("  nil:", reflect.ValueOf(nil).IsNil()) error
	fmt.Println("  (*int)(nil):", reflect.ValueOf((*int)(nil)).Elem().IsValid())
	fmt.Println()

	// 空结构体
	s := struct{}{}
	fmt.Println("  empty struct member:", reflect.ValueOf(s).FieldByName("").IsValid())
	fmt.Println("  empty struct func:", reflect.ValueOf(s).MethodByName("").IsValid())
	fmt.Println()

	m := map[int]int{}
	fmt.Println("  invalid key:", reflect.ValueOf(m).MapIndex(reflect.ValueOf(3)).IsValid())
	fmt.Println()
}
