#encoding:utf-8

def quick_sort(lst):
    if len(lst) <= 1:
        return lst
    less = []
    greater = []
    pivot = lst.pop()
    for item in lst:
        if item < pivot:
            less.append(item)
        else:
            greater.append(item)
    lst.append(pivot)
    l = quick_sort(less) + [pivot] + quick_sort(greater)
    print(l)
    return l

print(quick_sort([1, 3, 4, 5, 2, 2, 1, 10, 3, 0, -1 ]))