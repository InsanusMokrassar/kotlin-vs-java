package com.github.insanusmokrassar.KotlinVSJava.JavaComparisonModule.ContactBookJava.API;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ContactsBook implements List<Contact> {
    private final List<Contact> realList = new ArrayList<>();

    @Override
    public int size() {
        return realList.size();
    }

    @Override
    public boolean isEmpty() {
        return realList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return realList.contains(o);
    }

    @NotNull
    @Override
    public Iterator<Contact> iterator() {
        return realList.iterator();
    }

    @NotNull
    @Override
    public Object[] toArray() {
        return realList.toArray();
    }

    @NotNull
    @Override
    public <T> T[] toArray(@NotNull T[] a) {
        return realList.toArray(a);
    }

    @Override
    public boolean add(Contact contact) {
        return realList.add(contact);
    }

    @Override
    public boolean remove(Object o) {
        return realList.remove(o);
    }

    @Override
    public boolean containsAll(@NotNull Collection<?> c) {
        return realList.containsAll(c);
    }

    @Override
    public boolean addAll(@NotNull Collection<? extends Contact> c) {
        return realList.addAll(c);
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends Contact> c) {
        return realList.addAll(index, c);
    }

    @Override
    public boolean removeAll(@NotNull Collection<?> c) {
        return realList.removeAll(c);
    }

    @Override
    public boolean retainAll(@NotNull Collection<?> c) {
        return realList.retainAll(c);
    }

    @Override
    public void clear() {
        realList.clear();
    }

    @Override
    public boolean equals(Object o) {
        return realList.equals(o);
    }

    @Override
    public int hashCode() {
        return realList.hashCode();
    }

    @Override
    public Contact get(int index) {
        return realList.get(index);
    }

    @Override
    public Contact set(int index, Contact element) {
        return realList.set(index, element);
    }

    @Override
    public void add(int index, Contact element) {
        realList.add(index, element);
    }

    @Override
    public Contact remove(int index) {
        return realList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return realList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return realList.lastIndexOf(o);
    }

    @NotNull
    @Override
    public ListIterator<Contact> listIterator() {
        return realList.listIterator();
    }

    @NotNull
    @Override
    public ListIterator<Contact> listIterator(int index) {
        return realList.listIterator(index);
    }

    @NotNull
    @Override
    public List<Contact> subList(int fromIndex, int toIndex) {
        return realList.subList(fromIndex, toIndex);
    }
}
