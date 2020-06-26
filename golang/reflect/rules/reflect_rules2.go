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

	var a int = 1024
	valueOfA := reflect.ValueOf(a)

	// 获取interface{}类型，通过类型断言转换为指定类型
	var ret1 int = valueOfA.Interface().(int)
	// 获取Int类型，再强制转换类型
	var ret2 int = int(valueOfA.Int())
	fmt.Println(ret1, ret2)
}
