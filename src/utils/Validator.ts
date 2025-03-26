import { Colors, TaskPriority, TaskStatus } from '@enums/index';

type IsValidEnum = TaskPriority | TaskStatus;

type ValidationField = {
  value: string;
  enum: Record<string, IsValidEnum>;
  fieldName: string;
};

export class Validator {
  private errors: string[] = [];

  validate(fields: ValidationField[]) {
    fields.forEach(({ value, enum: enumType, fieldName }) => {
      const validValues = Object.values(enumType) as string[];
      const formattedValidValues = validValues
        .join(', ')
        .replace(/, ([^,]+)$/, ' or $1'); // output: x, y or z.

      if (value && !validValues.includes(value)) {
        this.errors.push(
          `Invalid value for ${fieldName}: "${value}". Value must be: ${formattedValidValues}.`,
        );
      }
    });

    if (this.errors.length > 0) {
      console.log(Colors.red + this.errors.join('\n'));
      process.exit(1);
    }
  }
}
