package main

import "fmt"
import "io"

func main() {
	fmt.Println("接口")

	var dataWriter DataWriter

	f := new(file)
	dataWriter = f

	dataWriter.WriteData("data")

	var writer DataWriter
	writer = new(file)
	writer.WriteData("database")

	writer = new(Database)
	writer.WriteData("database")
	fmt.Println()

	fmt.Println("2.接口的实现:")
	// 一个类型实现多个接口:Writer/Closer
	s := new(Socket)
	useWriter(s)
	useCloser(s)
	// 多个类型实现相同接口
	var service Service
	service = new(GameService)
	service.Start()
	service.Log("hello")
	fmt.Println()

	fmt.Println("3.类型断言")
	var x interface{}
	x = 10
	value,ok := x.(int)
	fmt.Print("x=", value, ",ok=", ok, "\n")

	var n interface{}
	n = nil
	value1, ok := n.(int)
	fmt.Println(value1, ok)
	fmt.Println()
}

type DataWriter interface {
	WriteData(data interface{}) error
}

type file struct {

}

type Database struct {

}

func (d *file) WriteData(data interface{}) error {
	fmt.Println("writeData:", data)
	return nil
}

func (database Database) WriteData(data interface{}) error {
	fmt.Println("database Writer", data)
	return nil
}

// #接口的实现
type Socket struct {

}
func (s Socket)Write(p []byte) (n int, err error) {
	return 0,nil
}
func (s Socket)Close() error {
	return nil
}

func useWriter(writer io.Writer) {
	writer.Write(nil)
}
func useCloser(closer io.Closer) {
	closer.Close()
}


// # 接口的实现
type Service interface {
	Start()
	Log(string)
}

type Logger struct {

}
func (logger Logger) Log(str string) {
	fmt.Println("service log:", str)
}

type GameService struct {
	Logger
}

func (game GameService) Start() {
	fmt.Println("service start")
}
