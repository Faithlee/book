package main

import (
	"fmt"
)

func main() {
	ford := &Car{"Fiesta", "Ford", 2008}
	bmw  := &Car{"XL 450", "BMW", 2011}
	merc := &Car{"D600",  "Mercedes", 2009}
	bmw2 := &Car{"X 800", "BMW", 2008}
	allCars := Cars([]*Car{ford, bmw, merc, bmw2})
	fmt.Println(allCars)
	//fmt.Printf("%+v", allCars) 
	//allCars.Process(func(car *Car){
	//	fmt.Printf("%v\n", *car)
	//})

	// find car
	bmwCars := allCars.FindAll(func(car *Car)bool{
		return car.Manufacturer == "BMW" && car.BuildYear > 2010;
	})
	bmwCars.Process(func(car *Car) {
		fmt.Printf("%v\n", car)
	})

	manufacturers := []string{"Ford", "Aston Martin", "Land Rover", "BMW", "Jaguar"}
	sortedAppender, sortedCars := MakeSortedAppender(manufacturers)
	allCars.Process(sortedAppender)
	fmt.Println(sortedCars)
	bmwNum := len(sortedCars["BMW"])
	fmt.Printf("we have %d bmws\n", bmwNum)
}

type Any interface{}

type Car struct {
	Model	string
	Manufacturer	string
	BuildYear	int
}

type Cars []*Car

func (cars Cars) Process(f func(car *Car)) {
	for _, car := range cars {
		f(car)
	}
}

func (cars Cars) FindAll(f func(car *Car)bool) Cars {
	newCars := make([]*Car, 0)
	cars.Process(func(c *Car){
		if f(c) {
			newCars = append(newCars, c)
		}
	})

	return newCars
}

func MakeSortedAppender(manufacturers []string) (func(car *Car), map[string]Cars) {
	sortedCars := make(map[string]Cars)

	for _, m := range manufacturers {
		sortedCars[m] = make([]*Car, 0)
	}
	sortedCars["Default"] = make([]*Car, 0)

	appender := func(c *Car) {
		if _, ok := sortedCars[c.Manufacturer]; ok {
			sortedCars[c.Manufacturer] = append(sortedCars[c.Manufacturer], c)
		} else {
			sortedCars["Default"] = append(sortedCars["Default"], c)
		}
	}

	return appender, sortedCars
}

func (cars Cars) Map(f func(car *Car) Any) []Any {
	result := make([]Any, len(cars))
	i := 0
	cars.Process(func(c *Car) {
		result[i] = f(c)
		i++
	})

	return result
}
