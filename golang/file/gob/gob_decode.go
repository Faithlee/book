package main

import (
	"fmt"
	"os"
	"encoding/gob"
)

func main() {
	var data map[string]string

	file, err := os.Open("../data/golang.gob")
	if err != nil {
		fmt.Println("打开gob文件失败:", err)
		return
	}

	decoder := gob.NewDecoder(file)
	if err = decoder.Decode(&data); err != nil {
		fmt.Println("解析gob文件失败:", err)
		return
	}

	fmt.Printf("%v", data)
}
