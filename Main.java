package org.example;

public class Main {
    // Свойства
    private double costForOnePrisoner; // Стоимость содержания одного заключенного
    private int totalSentenceDuration; // Общая длительность наказания
    private boolean isVisitationDay; // День посещений
    private boolean isAllowed; // Разрешено ли посещение

    // Конструктор
    public Main(double costForOnePrisoner, int totalSentenceDuration, boolean isVisitationDay, boolean isAllowed) {
        this.costForOnePrisoner = costForOnePrisoner;
        this.totalSentenceDuration = totalSentenceDuration;
        this.isVisitationDay = isVisitationDay;
        this.isAllowed = isAllowed;
    }

    // Методы
    // Функция для расчета общей стоимости содержания всех заключенных
    public double calculateTotalAmount(int quantityPrisoner) {
        return costForOnePrisoner * quantityPrisoner;
    }

    // Функция для вычисления оставшихся дней от всего срока наказания
    public int calculateRemainingDays(int daysServed) {
        return totalSentenceDuration - daysServed;
    }

    // Функция для проверки возможности встречи с родными
    public boolean canHaveVisitation() {
        return isVisitationDay && isAllowed;
    }

    // Функция для выдачи штрафного времени за нарушение правил тюрьмы
    public int issuePenaltyTime(int originalTime, int violation) {
        return originalTime + violation * 30; // Например, за каждое нарушение правил добавляется 30 дней
    }

    // Функция, устанавливающая новый срок наказания в случае повторного правонарушения
    public int setNewSentenceDuration(int currentDuration, int repeatedViolations) {
        return currentDuration + repeatedViolations * 365; // Например, за каждое повторное нарушение добавляется год
    }

    // Функция для выдачи дополнительного бонуса за хорошее поведение
    public int rewardForGoodBehavior(int originalSentenceDuration) {
        return originalSentenceDuration / 5; // Например, 5% от исходной длительности наказания
    }

    // Функция для пересмотра срока наказания в случае отмены бонуса за хорошее поведение
    public int reviewSentenceDuration(int originalSentenceDuration, boolean bonusCancelled) {
        if (bonusCancelled) {
            // Если бонус отменен, удлиним срок наказания на 10%
            return originalSentenceDuration + (originalSentenceDuration / 10);
        } else {
            return originalSentenceDuration; // Возвращаем исходный срок наказания
        }
    }

    // Функция для учета дополнительного бонуса за отличное поведение
    public int extraRewardForExemplaryBehavior(int originalSentenceDuration, boolean exemplaryBehavior) {
        if (exemplaryBehavior) {
            // Если заключенный проявляет примерное поведение, сокращаем срок на 8% от исходного срока наказания
            return originalSentenceDuration - (originalSentenceDuration * 8 / 100);
        } else {
            return originalSentenceDuration / 5; // Возвращаем исходный бонус за хорошее поведение
        }
    }

    // Функция для учета условий рецидива преступлений при установлении нового срока наказания
    public int adjustSentenceDurationForRecidivism(int currentDuration, int repeatedViolations, boolean repeatOffense) {
        if (repeatOffense) {
            // Если произошел рецидив преступлений, увеличиваем срок наказания на количество повторных нарушений, умноженное на 2 года
            return currentDuration + (repeatedViolations * (2 * 365));
        } else {
            return currentDuration; // Возвращаем исходный срок наказания
        }
    }

    // Функция для пересчета срока наказания в случае получения амнистии
    public static int recalculateSentenceDuration(int currentDuration, boolean grantedAmnesty) {
        if (grantedAmnesty) {
            // Если амнистия предоставлена, срок наказания сокращается на половину
            return currentDuration / 2;
        } else {
            return currentDuration; // Возвращаем исходный срок наказания
        }
    }

    public static void main(String[] args) {
        Prisoner prisoner = new Prisoner("Белинская Яна", 2450, 20, true);

        // Выводим информацию о заключенном
        System.out.println("Информация о заключенном:");
        System.out.println(prisoner.toString());
        System.out.println();

        Prison prison = new Prison(1000.0, 365, true, true);

        // Пример использования функции для расчета общей стоимости содержания всех заключенных
        System.out.println("Общая стоимость для всех заключенных: $" + prison.calculateTotalAmount(50));

        // Пример использования функции для вычисления оставшихся дней от всего срока наказания
        System.out.println("Оставшиеся дни наказания: " + prison.calculateRemainingDays(100));

        // Пример использования функции для проверки возможности встречи с родными
        System.out.println("Можно в гости: " + prison.canHaveVisitation());

        // Пример использования функции для выдачи штрафного времени за нарушение правил тюрьмы
        int newSentenceDuration = prison.issuePenaltyTime(365, 2);
        System.out.println("Новый срок наказания после штрафа: " + newSentenceDuration + " дней");

        // Пример использования функции для установки нового срока наказания в случае повторного правонарушения
        int extendedDuration = prison.setNewSentenceDuration(365, 2);
        System.out.println("Новый срок наказания после повторного правонарушения: " + extendedDuration + " дней");

        // Пример использования функции для выдачи дополнительного бонуса за хорошее поведение
        int bonus = prison.rewardForGoodBehavior(365);
        System.out.println("Дополнительный бонус за хорошее поведение: " + bonus + " дней");

        // Пример использования функции для пересмотра срока наказания в случае отмены бонуса за хорошее поведение
        int reviewedDuration = prison.reviewSentenceDuration(365, true);
        System.out.println("Срок наказания после пересмотра: " + reviewedDuration + " дней");

        // Пример использования функции для учета дополнительного бонуса за отличное поведение
        int exemplaryBonus = prison.extraRewardForExemplaryBehavior(365, true);
        System.out.println("Дополнительный бонус за отличное поведение: " + exemplaryBonus + " дней");

        // Пример использования функции для учета условий рецидива преступлений при установлении нового срока наказания
        int adjustedDuration = prison.adjustSentenceDurationForRecidivism(365, 2, true);
        System.out.println("Срок наказания после учета рецидива: " + adjustedDuration + " дней");

        // Пример использования функции для пересчета срока наказания в случае получения амнистии
        int amnestyDuration = Main.recalculateSentenceDuration(365, true);
        System.out.println("Срок наказания после амнистии: " + amnestyDuration + " дней");
    }
}

// Класс Prison наследуется от класса main
class Prison extends Main {
    public Prison(double costForOnePrisoner, int totalSentenceDuration, boolean isVisitationDay, boolean isAllowed) {
        super(costForOnePrisoner, totalSentenceDuration, isVisitationDay, isAllowed);
    }
}


// Класс Prisoner
class Prisoner {
    private String name;
    private int prisonerID;
    private int age;
    private boolean isViolent;

    // Конструктор
    public Prisoner(String name, int prisonerID, int age, boolean isViolent) {
        this.name = name;
        this.prisonerID = prisonerID;
        this.age = age;
        this.isViolent = isViolent;
    }

    // Методы
    public String toString() {
        return "Имя: " + name + "\n" +
                "ID заключенного: " + prisonerID + "\n" +
                "Возраст: " + age + "\n" +
                "Находится в тюрьме: " + (isViolent ? "да" : "нет");
    }
}

