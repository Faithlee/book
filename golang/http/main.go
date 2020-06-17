package main

import "fmt"
import "log"
import "net/http"
import "io/ioutil"

func main() {
	http.HandleFunc("/", hello)
	http.HandleFunc("/index", index)
	fmt.Println("http server start success!\n")

	log.Fatal(http.ListenAndServe("192.168.137.139:8000",nil))
}

func hello(w http.ResponseWriter, r *http.Request) {
	// 输出内容到客户端
	fmt.Fprintf(w, "hello, golang")
}

func index(w http.ResponseWriter, r *http.Request) {
	// 加载静态文件
	content, err  := ioutil.ReadFile("./src/http/index.html")
	fmt.Println(content, err)
	w.Write(content)
}
