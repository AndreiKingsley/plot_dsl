package proto3

sealed interface BindingParameter

class MappingParameter<T>(var mapping: Data<T>? = null): BindingParameter

class AssigmentParameter<T>(var value: T? = null): BindingParameter
