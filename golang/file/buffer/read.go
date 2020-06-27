package main

import (
	"fmt"
	"os"
	"bufio"
	"strconv"
)

func main() {
	file, err := os.Open("../data/buffer.txt")
	if err != nil {
		fmt.Println("文件打开失败:", err)
		return
	}
	defer file.Close()

	buffer := make([]byte, 1024)

	reader := bufio.NewReader(file)
	data, err := reader.Read(buffer)
	if err != nil {
		fmt.Println("读取数据失败:", err)
		return
	}

	fmt.Println("读取的字节数:", strconv.Itoa(data))
	fmt.Println("读取的文件内容:", string(buffer))
}
