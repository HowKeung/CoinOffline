package com.jungel.coinoffline.eos.eos4j.utils;

import com.jungel.coinoffline.eos.eos4j.api.vo.BaseVo;
import com.jungel.coinoffline.eos.eos4j.api.vo.transaction.push.TxSign;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ObjectUtils {

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Bean2Map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> Bean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new LinkedHashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            map.put(fields[i].getName(), getFieldValueByName(fields[i].getName(), obj));
        }
        return map;
    }

    public static void writeBytes(Object vo, ByteBuffer bf) {
        Map<String, Object> params = null;
        if (vo instanceof Map) {
            params = (Map) vo;
        } else {
            params = Bean2Map(vo);
        }
        Map<String, Object> objMap = new LinkedHashMap<>();
        for (String key : params.keySet()) {
            Object obj = params.get(key);
            if (obj instanceof BaseVo || obj instanceof List || obj instanceof Map) {
                if ("authorization".equals(key)) {
                    bf.concat(ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                    print(key, ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                    for (Object ob : (List) obj) {
                        writeBytes(ob, bf);
                    }
                } else if ("data".equals(key)) {
                    ByteBuffer databf = new ByteBuffer();
                    writeBytes(obj, databf);
                    bf.concat(ByteUtils.writerVarint32(String.valueOf(databf.getBuffer().length)));
                    print("length", ByteUtils.writerVarint32(String.valueOf(databf.getBuffer().length)));
                    bf.concat(databf.getBuffer());
                    print("buffer", databf.getBuffer());
                } else if ("transaction_extensions".equals(key)) {

                } else {
                    objMap.put(key, obj);
                }
            } else {
                if ("chain_id".equals(key)) {
                    bf.concat(Hex.hexStringToBytes(obj.toString()));
                    print(key, Hex.hexStringToBytes(obj.toString()));
                } else if ("expiration".equals(key)) {
                    bf.concat(ByteUtils.writerUnit32(obj.toString()));
                    print(key, ByteUtils.writerUnit32(obj.toString()));
                } else if ("ref_block_num".equals(key)) {
                    bf.concat(ByteUtils.writerUnit16(obj.toString()));
                    print(key, ByteUtils.writerUnit16(obj.toString()));
                } else if ("ref_block_prefix".equals(key)) {
                    bf.concat(ByteUtils.writerUnit32(obj.toString()));
                    print(key, ByteUtils.writerUnit32(obj.toString()));
                } else if ("net_usage_words".equals(key)) {
                    bf.concat(ByteUtils.writerVarint32(obj.toString()));
                    print(key, ByteUtils.writerVarint32(obj.toString()));
                } else if ("max_cpu_usage_ms".equals(key)) {
                    bf.concat(ByteUtils.writerUnit8(obj.toString()));
                    print(key, ByteUtils.writerUnit8(obj.toString()));
                } else if ("delay_sec".equals(key)) {
                    bf.concat(ByteUtils.writerVarint32(obj.toString()));
                    print(key, ByteUtils.writerVarint32(obj.toString()));
                } else if ("account".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("name".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("actor".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("permission".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("from".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("to".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("quantity".equals(key)) {
                    bf.concat(ByteUtils.writerAsset(obj.toString()));
                    print(key, ByteUtils.writerAsset(obj.toString()));
                } else if ("memo".equals(key)) {
                    bf.concat(ByteUtils.writerString(obj.toString()));
                    print(key, ByteUtils.writerString(obj.toString()));
                } else if ("creator".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("owner".equals(key)) {
                    bf.concat(ByteUtils.writerKey(obj.toString()));
                    print(key, ByteUtils.writerKey(obj.toString()));
                } else if ("active".equals(key)) {
                    bf.concat(ByteUtils.writerKey(obj.toString()));
                    print(key, ByteUtils.writerKey(obj.toString()));
                } else if ("payer".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("receiver".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("bytes".equals(key)) {
                    bf.concat(ByteUtils.writerUnit32(obj.toString()));
                    print(key, ByteUtils.writerUnit32(obj.toString()));
                } else if ("stake_net_quantity".equals(key)) {
                    bf.concat(ByteUtils.writerAsset(obj.toString()));
                    print(key, ByteUtils.writerAsset(obj.toString()));
                } else if ("stake_cpu_quantity".equals(key)) {
                    bf.concat(ByteUtils.writerAsset(obj.toString()));
                    print(key, ByteUtils.writerAsset(obj.toString()));
                } else if ("transfer".equals(key)) {
                    bf.concat(ByteUtils.writerUnit8(obj.toString()));
                    print(key, ByteUtils.writerUnit8(obj.toString()));
                } else if ("voter".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("proxy".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("producer".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("close-owner".equals(key)) {
                    bf.concat(ByteUtils.writeName(obj.toString()));
                    print(key, ByteUtils.writeName(obj.toString()));
                } else if ("close-symbol".equals(key)) {
                    bf.concat(ByteUtils.writerSymbol(obj.toString()));
                    print(key, ByteUtils.writerSymbol(obj.toString()));
                }
            }
        }
        for (String key : objMap.keySet()) {
            Object obj = params.get(key);
            if ("context_free_actions".equals(key)) {
                bf.concat(ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                print(key, ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                for (Object ob : (List) obj) {
                    writeBytes(ob, bf);
                }
            } else if ("actions".equals(key)) {
                bf.concat(ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                print(key, ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                for (Object ob : (List) obj) {
                    writeBytes(ob, bf);
                }
            } else if ("producers".equals(key)) {
                bf.concat(ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                print(key, ByteUtils.writerVarint32(String.valueOf(((List) obj).size())));
                for (Object ob : (List) obj) {
                    Map<String, Object> mp = new HashMap<>();
                    mp.put("producer", ob);
                    writeBytes(mp, bf);
                }
            } else {
                writeBytes(obj, bf);
            }
        }
    }

    public static void writeTransferBytes(TxSign txSign, ByteBuffer bf) {
        if (txSign == null) {
            return;
        }
        bf.concat(Hex.hexStringToBytes(txSign.getChain_id()));
        if (txSign.getTransaction() == null) {
            return;
        }
        bf.concat(ByteUtils.writerUnit32(txSign.getTransaction().getExpiration().toString()));
        bf.concat(ByteUtils.writerUnit16(txSign.getTransaction().getRef_block_num().toString()));
        bf.concat(ByteUtils.writerUnit32(txSign.getTransaction().getRef_block_prefix().toString()));
        bf.concat(ByteUtils.writerVarint32(txSign.getTransaction().getNet_usage_words().toString()));
        bf.concat(ByteUtils.writerUnit8(txSign.getTransaction().getMax_cpu_usage_ms().toString()));
        bf.concat(ByteUtils.writerVarint32(txSign.getTransaction().getDelay_sec().toString()));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getContext_free_actions().size())));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getActions().size())));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getAccount()));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getName()));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getActions().get(0).getAuthorization().size())));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getAuthorization().get(0).getActor()));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getAuthorization().get(0).getPermission()));

        ByteBuffer databf = new ByteBuffer();
        writeBytes(txSign.getTransaction().getActions().get(0).getData(), databf);
        bf.concat(ByteUtils.writerVarint32(String.valueOf(databf.getBuffer().length)));

        Map<String, Object>  txData = (Map<String, Object>) txSign.getTransaction().getActions().get(0).getData();
        bf.concat(ByteUtils.writeName(txData.get("from").toString()));
        bf.concat(ByteUtils.writeName(txData.get("to").toString()));
        bf.concat(ByteUtils.writerAsset(txData.get("quantity").toString()));
        bf.concat(ByteUtils.writerString(txData.get("memo").toString()));
    }

    public static void writeAccountBytes(TxSign txSign, ByteBuffer bf) {
        if (txSign == null) {
            return;
        }
        bf.concat(Hex.hexStringToBytes(txSign.getChain_id()));
        if (txSign.getTransaction() == null) {
            return;
        }
        bf.concat(ByteUtils.writerUnit32(txSign.getTransaction().getExpiration().toString()));
        bf.concat(ByteUtils.writerUnit16(txSign.getTransaction().getRef_block_num().toString()));
        bf.concat(ByteUtils.writerUnit32(txSign.getTransaction().getRef_block_prefix().toString()));
        bf.concat(ByteUtils.writerVarint32(txSign.getTransaction().getNet_usage_words().toString()));
        bf.concat(ByteUtils.writerUnit8(txSign.getTransaction().getMax_cpu_usage_ms().toString()));
        bf.concat(ByteUtils.writerVarint32(txSign.getTransaction().getDelay_sec().toString()));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getContext_free_actions().size())));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getActions().size())));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getAccount()));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getName()));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getActions().get(0).getAuthorization().size())));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getAuthorization().get(0).getActor()));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(0).getAuthorization().get(0).getPermission()));

        ByteBuffer databf = new ByteBuffer();
        writeBytes(txSign.getTransaction().getActions().get(0).getData(), databf);
        bf.concat(ByteUtils.writerVarint32(String.valueOf(databf.getBuffer().length)));

        Map<String, Object>  txData = (Map<String, Object>) txSign.getTransaction().getActions().get(0).getData();
        bf.concat(ByteUtils.writeName(txData.get("creator").toString()));
        bf.concat(ByteUtils.writeName(txData.get("name").toString()));
        bf.concat(ByteUtils.writerKey(txData.get("owner").toString()));
        bf.concat(ByteUtils.writerKey(txData.get("active").toString()));

        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(1).getAccount()));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(1).getName()));
        bf.concat(ByteUtils.writerVarint32(String.valueOf(txSign.getTransaction().getActions().get(1).getAuthorization().size())));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(1).getAuthorization().get(0).getActor()));
        bf.concat(ByteUtils.writeName(txSign.getTransaction().getActions().get(1).getAuthorization().get(0).getPermission()));

        ByteBuffer databf2 = new ByteBuffer();
        writeBytes(txSign.getTransaction().getActions().get(1).getData(), databf2);
        bf.concat(ByteUtils.writerVarint32(String.valueOf(databf2.getBuffer().length)));

        Map<String, Object>  txData2 = (Map<String, Object>) txSign.getTransaction().getActions().get(1).getData();
        bf.concat(ByteUtils.writeName(txData2.get("payer").toString()));
        bf.concat(ByteUtils.writeName(txData2.get("receiver").toString()));
        bf.concat(ByteUtils.writerUnit32(txData2.get("bytes").toString()));
    }

    private static void concat(byte[] bytes, ByteBuffer bf) {
        if (bytes != null) {
            bf.concat(bytes);
        }
    }

    private static void print(String name, byte[] bytes) {
        if (bytes == null) {
            return;
        }
        System.out.print(name + " : ");
        for (byte b : bytes) {
            System.out.print(b);
        }
        System.out.println("");
    }
}
