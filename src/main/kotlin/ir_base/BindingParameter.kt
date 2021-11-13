package ir_base

sealed interface BindingParameter

class MappingParameter(var dimension: Int? = null): BindingParameter

class AssigmentParameter<T>(var value: T? = null): BindingParameter
