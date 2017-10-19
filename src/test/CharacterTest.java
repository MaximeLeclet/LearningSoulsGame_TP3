package test;

import com.sun.istack.internal.Nullable;
import lsg.armor.ArmorItem;
import lsg.buffs.rings.Ring;
import lsg.weapons.Weapon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

public class CharacterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Nullable
    private Constructor<?> searchDefaultConstructor(Class<?> c) {
        for (Constructor<?> constructor : c.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }

    @Test
    public void testSkinThicknessAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Field f = c.getDeclaredField("skinThickness");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), float.class);

            Constructor<?> constructor = searchDefaultConstructor(c);
            Object o = constructor.newInstance();

            f.setAccessible(true);

            Assert.assertEquals((float) (f.get(o)), 20f, 0f);

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named skinThickness");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testSkinThicknessGetterSetter() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Method mg = c.getDeclaredMethod("getSkinThickness");
            Method ms = c.getDeclaredMethod("setSkinThickness", float.class);

            Assert.assertEquals(mg.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(ms.getModifiers(), Modifier.PROTECTED);
            Assert.assertTrue("wrong return type (float) of getSkinThickness", mg.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have accessors for skinThickness attribute");
        }
    }

    @Test
    public void existArmorItemClass() {
        try {
            Class.forName("lsg.armor.ArmorItem");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ArmorItem in package lsg.armor");
        }
    }

    @Test
    public void testArmorItemAttributes() {
        try {
            Class<?> c = Class.forName("lsg.armor.ArmorItem");
            Field f1 = c.getDeclaredField("name");
            Field f2 = c.getDeclaredField("armorValue");

            Assert.assertEquals(f1.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f2.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f1.getType(), String.class);
            Assert.assertEquals(f2.getType(), float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ArmorItem");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attribute: name and armorValue");
        }
    }

    @Test
    public void testArmorItemGetterSetter() {
        try {
            Class<?> c = Class.forName("lsg.armor.ArmorItem");
            Method m1 = c.getDeclaredMethod("getName");
            Method m2 = c.getDeclaredMethod("getArmorValue");

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (string) of getName", m1.getReturnType() == String.class);
            Assert.assertTrue("wrong return type (float) of getArmorValue", m2.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ArmorItem");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have accessors for name and armorValue attributes");
        }
    }

    @Test
    public void testArmorItemConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.ArmorItem");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, float.class);

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.ArmorItem");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a constructor with two parameters (String and float) for lsg.armor.ArmorItem class");
        }
    }

    @Test
    public void existArmorItemToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.ArmorItem");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, float.class);
            Object o = constructor.newInstance("Black Witch Veil", 4.6f);
            Method ts = c.getDeclaredMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Black Witch Veil(4.6)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.ArmorItem");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existBlackWitchVeilClass() {
        try {
            Class.forName("lsg.armor.BlackWitchVeil");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called BlackWitchVeil in package lsg.armor");
        }
    }

    @Test
    public void testBlackWitchVeilConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor = c.getDeclaredConstructor();

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.BlackWitchVeil");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a default constructor for lsg.armor.BlackWitchVeil class");
        }
    }

    @Test
    public void testBlackWitchVeilToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Black Witch Veil(4.6)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.BlackWitchVeil");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existDragonSlayerLeggingsClass() {
        try {
            Class.forName("lsg.armor.DragonSlayerLeggings");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called DragonSlayerLeggings in package lsg.armor");
        }
    }

    @Test
    public void testDragonSlayerLeggingsConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.DragonSlayerLeggings");
            Constructor<?> constructor = c.getDeclaredConstructor();

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.DragonSlayerLeggings");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a default constructor for lsg.armor.DragonSlayerLeggings class");
        }
    }

    @Test
    public void testDragonSlayerLeggingsToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.DragonSlayerLeggings");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Dragon Slayer Leggings(10.2)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.DragonSlayerLeggings");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existRingedKnightArmorClass() {
        try {
            Class.forName("lsg.armor.RingedKnightArmor");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called RingedKnightArmor in package lsg.armor");
        }
    }

    @Test
    public void testRingedKnightArmorConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor = c.getDeclaredConstructor();

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.RingedKnightArmor");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a default constructor for lsg.armor.RingedKnightArmor class");
        }
    }

    @Test
    public void testRingedKnightArmorToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Ringed Knight Armor(14.99)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.RingedKnightArmor");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void notExistToStringInArmorItemSubclasses() {
        Class<?> c1 = null;
        Class<?> c2 = null;
        Class<?> c3 = null;
        Method ts;

        try {
            c1 = Class.forName("lsg.armor.BlackWitchVeil");
            c2 = Class.forName("lsg.armor.DragonSlayerLeggings");
            c3 = Class.forName("lsg.armor.RingedKnightArmor");
            c1.getDeclaredMethod("toString");
            Assert.fail("toString method should be not override");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called BlackWitchVeil, DragonSlayerLeggings, RingedKnightArmor");
        } catch (NoSuchMethodException e1) {
            try {
                c2.getDeclaredMethod("toString");
                Assert.fail("toString method should be not override");
            } catch (NoSuchMethodException e2) {
                try {
                    c3.getDeclaredMethod("toString");
                    Assert.fail("toString method should be not override");
                } catch (NoSuchMethodException e3) {
                    Assert.assertTrue(true);
                }
            }
        }
    }

    @Test
    public void testArmorAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Field f = c.getDeclaredField("armor");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), lsg.armor.ArmorItem[].class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named armor");
        }
    }

    @Test
    public void testMaxArmorPiecesAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class);

            if (c == null) {
                Assert.fail("should have a constructor with a string parameter");
            } else {
                Field f = c.getDeclaredField("MAX_ARMOR_PIECES");
                Object o = constructor.newInstance("supertoto");

                f.setAccessible(true);

                Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE | Modifier.STATIC);
                Assert.assertEquals(f.getType(), int.class);
                Assert.assertEquals((int) (f.get(o)), 3);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an static attribute named MAX_ARMOR_PIECES");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        }
    }

    @Test
    public void testHeroConstructorWithArmor() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class);

            if (constructor == null) {
                Assert.fail("should have a constructor with a string parameter");
            } else {
                Object o = constructor.newInstance("supertoto");
                Field f = c.getDeclaredField("armor");

                f.setAccessible(true);

                lsg.armor.ArmorItem[] armor = (lsg.armor.ArmorItem[]) (f.get(o));

                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], null);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (NoSuchFieldException e) {
            Assert.fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        }
    }

    @Test
    public void testSetArmorItem() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);

            {
                Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
                Constructor<?> constructor2 = c2.getDeclaredConstructor();
                Object o2 = constructor2.newInstance();

                m1.invoke(o1, o2, 4);

                Field f = c1.getDeclaredField("armor");

                f.setAccessible(true);

                lsg.armor.ArmorItem[] armor = (lsg.armor.ArmorItem[]) (f.get(o1));

                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], null);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);

                m1.invoke(o1, o2, 0);

                armor = (lsg.armor.ArmorItem[]) (f.get(o1));
                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], null);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);
            }
            {
                Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
                Constructor<?> constructor2 = c2.getDeclaredConstructor();
                Object o2 = constructor2.newInstance();

                m1.invoke(o1, o2, 1);

                Field f = c1.getDeclaredField("armor");

                f.setAccessible(true);

                lsg.armor.ArmorItem[] armor = (lsg.armor.ArmorItem[]) (f.get(o1));

                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], o2);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called setArmorItem in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (NoSuchFieldException e) {
            Assert.fail("NoSuchFieldException");
        }
    }

    @Test
    public void testGetTotalArmor() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("getTotalArmor");
            Method m2 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (float) of getTotalArmor", m1.getReturnType() == float.class);

            Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();

            m2.invoke(o1, constructor2.newInstance(), 1);
            m2.invoke(o1, constructor2.newInstance(), 2);
            m2.invoke(o1, constructor2.newInstance(), 3);

            Assert.assertEquals((float) (m1.invoke(o1)), 13.8f, 0.01f);

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called getTotalArmor in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testArmorToString() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);
            Method m2 = c1.getMethod("armorToString");

            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (String) of armorToString", m2.getReturnType() == String.class);

            Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Class<?> c3 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();

            m1.invoke(o1, constructor2.newInstance(), 1);
            m1.invoke(o1, constructor3.newInstance(), 3);

            Assert.assertEquals((String) (m2.invoke(o1)), "ARMOR   1:Black Witch Veil(4.6)           2:empty                           3:Ringed Knight Armor(14.99)    TOTAL:19.59");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called armorToString in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testGetArmorItems() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);
            Method m2 = c1.getMethod("getArmorItems");

            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (lsg.armor.ArmorItem[]) of getArmorItems", m2.getReturnType() == lsg.armor.ArmorItem[].class);

            Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Object o2 = constructor2.newInstance();
            Class<?> c3 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();
            Object o3 = constructor3.newInstance();

            m1.invoke(o1, o2, 1);
            m1.invoke(o1, o3, 3);

            lsg.armor.ArmorItem[] list = (lsg.armor.ArmorItem[]) (m2.invoke(o1));

            Assert.assertEquals(list.length, 2);
            Assert.assertEquals(list[0], o2);
            Assert.assertEquals(list[1], o3);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called getArmorItems in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testMain() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            Assert.assertEquals("ARMOR   1:Black Witch Veil(4.6)           2:empty" +
                    "                           3:Ringed Knight Armor(14.99)    TOTAL:19.59", list[0]);
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (ClassNotFoundException e) {
            Assert.fail("ClassNotFoundException");
        }
    }

    @Test
    public void existComputeProtectionInCharacterClass() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Method m = c.getDeclaredMethod("computeProtection");

            Assert.assertEquals(c.getModifiers(), Modifier.PUBLIC | Modifier.ABSTRACT);
            Assert.assertEquals(m.getModifiers(), Modifier.PROTECTED | Modifier.ABSTRACT);
            Assert.assertTrue("wrong return type (float) of computeProtection", m.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called computeProtection in Character class");
        }
    }

    @Test
    public void existComputeProtectionInMonsterClass() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Constructor<?> constructor1 = c.getDeclaredConstructor(java.lang.String.class);
            Object o = constructor1.newInstance("supertoto");
            Method m1 = c.getDeclaredMethod("computeProtection");
            Method m2 = c.getDeclaredMethod("getSkinThickness");

            m1.setAccessible(true);

            Assert.assertEquals(m1.getModifiers(), Modifier.PROTECTED);
            Assert.assertTrue("wrong return type (float) of computeProtection", m1.getReturnType() == float.class);
            Assert.assertEquals((float) (m1.invoke(o)), (float) (m2.invoke(o)), 0.01f);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called computeProtection in Monster class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existComputeProtectionInHeroClass() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c.getDeclaredConstructor(java.lang.String.class);
            Object o = constructor1.newInstance("supertoto");
            Method m1 = c.getDeclaredMethod("computeProtection");
            Method m2 = c.getDeclaredMethod("getTotalArmor");

            m1.setAccessible(true);

            Assert.assertEquals(m1.getModifiers(), Modifier.PROTECTED);
            Assert.assertTrue("wrong return type (float) of computeProtection", m1.getReturnType() == float.class);
            Assert.assertEquals((float) (m1.invoke(o)), (float) (m2.invoke(o)), 0.01f);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called computeProtection in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testHeroToString() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Method m1 = c1.getDeclaredMethod("setArmorItem", ArmorItem.class, int.class);
            Method ts = c1.getMethod("toString");
            Method m2 = c1.getMethod("getRings");

            Class<?> c2 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Object o2 = constructor2.newInstance();

            m1.setAccessible(true);

            m1.invoke(o1, o2, 1);
            if (m2 == null) {
                Assert.assertEquals((String) (ts.invoke(o1)), "[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 14.99    (ALIVE)");
            } else {
                Assert.assertEquals((String) (ts.invoke(o1)), "[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 14.99     BUFF:  0.00    (ALIVE)");
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero and lsg.armor.RingedKnightArmor");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called setArmorItem in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testGetHitWith() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Method m1 = c1.getDeclaredMethod("setArmorItem", ArmorItem.class, int.class);
            Method m2 = c1.getMethod("getHitWith", int.class);

            Class<?> c2 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Object o2 = constructor2.newInstance();
            Class<?> c3 = Class.forName("lsg.armor.DragonSlayerLeggings");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();
            Object o3 = constructor3.newInstance();

            m1.setAccessible(true);

            m1.invoke(o1, o2, 1);
            m1.invoke(o1, o3, 2);
            Assert.assertEquals((int) (m2.invoke(o1, 10)), 7);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero, lsg.armor.RingedKnightArmor and lsg.armor.DragonSlayerLeggings");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called setArmorItem and getHitWith in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existLycanthropeClass() {
        try {
            Class.forName("lsg.characters.Lycanthrope");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Lycanthrope in package lsg.characters");
        }
    }

    @Test
    public void existLycanthropeInheritance() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Monster");
            Class<?> c2 = Class.forName("lsg.characters.Lycanthrope");

            Assert.assertTrue("Lycanthrope should be a subclass of Monster", c1.isAssignableFrom(c2));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called Monster and Lycanthrope in package lsg.charaters");
        }
    }

    @Test
    public void testLycanthropeDefaultValues() {
        try {
            Class<?> c = Class.forName("lsg.characters.Lycanthrope");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m1 = c.getMethod("getSkinThickness");
            Method m2 = c.getMethod("getWeapon");
            Method m3 = c.getMethod("getName");

            Assert.assertEquals((float)(m1.invoke(o)), 30.f, 0.01f);
            Assert.assertEquals(((Weapon)(m2.invoke(o))).getClass(), lsg.weapons.Claw.class);
            Assert.assertEquals((String)(m3.invoke(o)), "Lycanthrope");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Lycanthrope in package lsg.charaters");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called getWeapon and getSkinThickness in Lycanthrope class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testRingsAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Field f = c.getDeclaredField("rings");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), Ring[].class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named rings");
        }
    }

    @Test
    public void testMaxRingsAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class);

            if (c == null) {
                Assert.fail("should have a constructor with a string parameter");
            } else {
                Field f = c.getDeclaredField("MAX_RINGS");
                Object o = constructor.newInstance("supertoto");

                f.setAccessible(true);

                Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE | Modifier.STATIC);
                Assert.assertEquals(f.getType(), int.class);
                Assert.assertEquals((int) (f.get(o)), 2);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an static attribute named MAX_RINGS");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        }
    }

    @Test
    public void testHeroConstructorWithRings() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class);

            if (constructor == null) {
                Assert.fail("should have a constructor with a string parameter");
            } else {
                Object o = constructor.newInstance("supertoto");
                Field f = c.getDeclaredField("rings");

                f.setAccessible(true);

                Ring[] rings = (Ring[]) (f.get(o));

                Assert.assertEquals(rings.length, 2);
                Assert.assertEquals(rings[0], null);
                Assert.assertEquals(rings[1], null);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (NoSuchFieldException e) {
            Assert.fail("NoSuchFieldException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        }
    }

    @Test
    public void testSetRings() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setRing", Ring.class, int.class);

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);

            {
                Class<?> c2 = Class.forName("lsg.buffs.rings.RingOfDeath");
                Constructor<?> constructor2 = c2.getDeclaredConstructor();
                Object o2 = constructor2.newInstance();

                m1.invoke(o1, o2, 3);

                Field f = c1.getDeclaredField("rings");

                f.setAccessible(true);

                Ring[] rings = (Ring[]) (f.get(o1));

                Assert.assertEquals(rings.length, 2);
                Assert.assertEquals(rings[0], null);
                Assert.assertEquals(rings[1], null);

                m1.invoke(o1, o2, 0);

                rings = (Ring[]) (f.get(o1));
                Assert.assertEquals(rings.length, 2);
                Assert.assertEquals(rings[0], null);
                Assert.assertEquals(rings[1], null);
            }
            {
                Class<?> c2 = Class.forName("lsg.buffs.rings.RingOfDeath");
                Constructor<?> constructor2 = c2.getDeclaredConstructor();
                Object o2 = constructor2.newInstance();

                m1.invoke(o1, o2, 1);

                Field f = c1.getDeclaredField("rings");

                f.setAccessible(true);

                Ring[] rings = (Ring[]) (f.get(o1));

                Assert.assertEquals(rings.length, 2);
                Assert.assertEquals(rings[0], o2);
                Assert.assertEquals(rings[1], null);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called setRing in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (NoSuchFieldException e) {
            Assert.fail("NoSuchFieldException");
        }
    }

    @Test
    public void testTalismanAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Field f = c.getDeclaredField("talisman");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), lsg.buffs.talismans.Talisman.class);

            Constructor<?> constructor = searchDefaultConstructor(c);
            Object o = constructor.newInstance();

            f.setAccessible(true);

            Assert.assertEquals(f.get(o), null);

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named talisman");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testTalismanGetterSetter() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Method mg = c.getDeclaredMethod("getTalisman");
            Method ms = c.getDeclaredMethod("setTalisman", lsg.buffs.talismans.Talisman.class);

            Assert.assertEquals(mg.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(ms.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (float) of getTalisman", mg.getReturnType() == lsg.buffs.talismans.Talisman.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have accessors for talisman attribute");
        }
    }

    @Test
    public void existComputeBuffInCharacterClass() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Method m = c.getDeclaredMethod("computeBuff");

            Assert.assertEquals(c.getModifiers(), Modifier.PUBLIC | Modifier.ABSTRACT);
            Assert.assertEquals(m.getModifiers(), Modifier.PROTECTED | Modifier.ABSTRACT);
            Assert.assertTrue("wrong return type (float) of computePBuff", m.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called computeBuff in Character class");
        }
    }

    @Test
    public void existComputeBuffInMonsterClass() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Monster");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");

            Class<?> c2 = Class.forName("lsg.buffs.talismans.NoonGift");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Object o2 = constructor2.newInstance();

            Method m1 = c1.getDeclaredMethod("computeBuff");
            Method m2 = c1.getDeclaredMethod("getTalisman");
            Method m3 = c1.getDeclaredMethod("setTalisman", lsg.buffs.talismans.Talisman.class);
            Method m4 = c2.getMethod("computeBuffValue");

            m1.setAccessible(true);
            m3.setAccessible(true);
            m4.setAccessible(true);

            m3.invoke(o1, o2);
            Assert.assertEquals(m1.getModifiers(), Modifier.PROTECTED);
            Assert.assertTrue("wrong return type (float) of computeBuff", m1.getReturnType() == float.class);
            Assert.assertEquals((float) (m1.invoke(o1)), (float)(m4.invoke((lsg.buffs.talismans.Talisman) (m2.invoke(o1)))), 0.01f);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called computeBuff in Monster class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existComputeBuffInHeroClass() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c.getDeclaredConstructor(java.lang.String.class);
            Method m1 = c.getDeclaredMethod("computeBuff");

            m1.setAccessible(true);

            Assert.assertEquals(m1.getModifiers(), Modifier.PROTECTED);
            Assert.assertTrue("wrong return type (float) of computeBuff", m1.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called computeBuff in Hero class");
        }
    }

}