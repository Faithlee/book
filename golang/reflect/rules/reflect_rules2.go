package main

import (
	"fmt"
	"reflect"
)

// 反射对象类型转换为接口变量类型
func main() {
	var x float64 = 3.14

	v := reflect.ValueOf(x)

	y := v.Interface().(float64)
	fmt.Println(v,y)

	fmt.Printf("value is %7.2e\n", v.Interface())
}
