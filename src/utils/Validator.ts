import { Colors, TaskPriority, TaskStatus } from '@enums/index';

type IsValidEnum = TaskPriority | TaskStatus;

export class Validator {
  private errors: string[] = [];

  validateField(
    value: string,
    enumType: Record<string, IsValidEnum>,
    fieldName: string,
  ) {
    const validValues = Object.values(enumType) as string[];

    if (value && !validValues.includes(value)) {
      this.errors.push(
        `Invalid ${fieldName} value: "${value}". Valid values are: ${validValues.join(', ')}.`,
      );
    }
  }

  validate(status: TaskStatus, priority: TaskPriority) {
    this.validateField(status, TaskStatus, 'status');
    this.validateField(priority, TaskPriority, 'priority');

    if (this.errors.length > 0) {
      console.log(Colors.red + this.errors.join('\n'));
      process.exit(1);
    }
  }
}
