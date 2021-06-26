package com.screennetwork.simplebreak.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author http://github.com/kauepalota
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tuple<K,V> {
    private K key;
    private V value;
}