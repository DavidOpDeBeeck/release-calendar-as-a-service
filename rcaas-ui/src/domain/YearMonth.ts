export class YearMonth {

    private static monthNames = [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    ];

    static parseDate(date: Date): YearMonth {
        return new YearMonth(date.getFullYear(), date.getMonth() + 1);
    }

    constructor(public year: number,
                public month: number) {
    }

    previous(): YearMonth {
        return this.month === 1
            ? new YearMonth(this.year - 1, 12)
            : new YearMonth(this.year, this.month - 1)
    }

    next(): YearMonth {
        return this.month === 12
            ? new YearMonth(this.year + 1, 1)
            : new YearMonth(this.year, this.month + 1)
    }

    getMonthName() {
        return YearMonth.monthNames[this.month - 1];
    }

    toFormattedString(): string {
        return `${this.year}-${this.month}`;
    }
}