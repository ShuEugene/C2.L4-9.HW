package auxiliaryLibrary;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DataService {

    //  correctParameters
    public static boolean isCorrect(String parameter) {
        return parameter != null && !parameter.isBlank() && !parameter.isEmpty();
    }

    public static boolean isCorrect(Collection<?> list) {
        return list != null && list.size() > 0;
    }

    public static <T> boolean isCorrect(T[] array) {
        return array != null && getNotNullObjectsNumber(array) > 0;
    }

    //  notNullObjects
    public static int getIndexOfLastNotNullObject(Object[] objects) {
        if (objects == null) {
            return -1;
        }
        int lastNotNullObjectIndex = -1;
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                lastNotNullObjectIndex = i;
            }
        }
        return lastNotNullObjectIndex;
    }

    public static int getNotNullObjectsNumber(Object[] objects) {
        if (objects == null) return 0;
        int numberOfNNObjects = objects.length;
        for (Object curObj :
                objects) {
            if (curObj == null) --numberOfNNObjects;
        }
        return numberOfNNObjects;
    }

    public static <T> T[] getNotNullObjects(T[] objects) {
        int numberOfNNObjects = getNotNullObjectsNumber(objects);
        if (numberOfNNObjects < 1) return null;
        T[] nnObjects = Arrays.copyOf(objects, numberOfNNObjects);
        int validObjectIndex = -1;
        for (T curObj : objects) {
            if (curObj != null) nnObjects[++validObjectIndex] = curObj;
        }
        return nnObjects;
    }

    public static int getNotNullObjectsNumber(Object[][] objects) {
        if (objects == null) return 0;
        int numberOfNNObjects = 0;
        for (Object[] curOneDimArray :
                objects) {
            for (Object curObject :
                    curOneDimArray) {
                if (curObject != null) {
                    ++numberOfNNObjects;
                }
            }
        }
        return numberOfNNObjects;
    }

    public static <T> T[][] getNotNullObjects(T[][] twoDimArray) {
        int numberOfNNObjects = getNotNullObjectsNumber(twoDimArray);
        if (numberOfNNObjects <= 0) return null;
        int nnObjectsArrayLenth = 0;
        T[][] nnObjects = Arrays.copyOf(twoDimArray, nnObjectsArrayLenth);
        for (T[] curOneDimArray :
                twoDimArray) {
            int numberOfOneDimArrayNNObjects = getNotNullObjectsNumber(curOneDimArray);
            if (numberOfOneDimArrayNNObjects > 0) {
                nnObjects = Arrays.copyOf(nnObjects, nnObjectsArrayLenth + 1);
                nnObjects[nnObjectsArrayLenth++] = getNotNullObjects(curOneDimArray);
            }
        }
        return nnObjects;
    }

    //  matches
    public static <T> int getFirstMatchIndex(T[] array, T target) {
        if (!isCorrect(array) || target == null) {
            return -1;
        }
        int index;
        for (index = 0; index < array.length; ++index) {
            if (array[index].equals(target)) {
                return index;
            }
        }
        return -1;
    }
}
