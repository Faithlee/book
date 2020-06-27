package main

import (
	"fmt"
	"bytes"
	"os"
	"encoding/binary"
)

type Url struct {
	Name int32
}

func main() {
	file, err := os.Create("../data/out.bin")
	if err != nil {
		fmt.Println("文件创建失败:", err)
		return
	}
	defer file.Close()

	for i := 1; i < 10; i++ {
		data := Url{
			int32(i),
		}

		var buf bytes.Buffer
		binary.Write(&buf, binary.LittleEndian, data)
		bytes := buf.Bytes()

		_, err = file.Write(bytes)
		if err != nil {
			fmt.Println("编码失败:", err.Error())
			return
		}
	}

	fmt.Println("编码成功")
}
