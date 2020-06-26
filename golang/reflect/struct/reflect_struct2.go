package main

import (
	"fmt"
	"reflect"
)

type dummy struct {
	a int
	b string
	float32
	bool
	next *dummy
}

// 反射结构体多层结构
func main() {
	d := reflect.ValueOf(dummy{
		next: &dummy{},
	})

	fmt.Println("NumField:", d.NumField())

	floatField := d.Field(2)
	fmt.Println("Field float:", floatField.Type())

	bType := d.FieldByName("b").Type()
	fmt.Println("Field b:", bType)

	// 根据索引查找多层结构体字段的值
	nextA := d.FieldByIndex([]int{4, 0}).Type()
	fmt.Println("Field next.a", nextA)
}


