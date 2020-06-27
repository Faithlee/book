package main

import (
	"fmt"
	"os"
	"bytes"
	"encoding/binary"
)

type Url struct {
	Name int32
}

func main() {
	file, err := os.Open("../data/out.bin")
	defer file.Close()
	if err != nil {
		fmt.Println("文件打开失败:", err.Error())
		return
	}

	url := Url{}
	for i := 1; i < 10; i++ {
		data := make([]byte, 4)
		_, err := file.Read(data)
		if err != nil {
			fmt.Println("解码失败:", err.Error())
			return
		}

		buffer := bytes.NewBuffer(data)
		err = binary.Read(buffer, binary.LittleEndian, &url)
		if err != nil {
			fmt.Println("binary文件读取失败:", err)
			return
		}

		fmt.Printf("第%d个值为:%v\n", i, url)
	}
}
