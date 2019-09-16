export class Receipt {
	grandTotal: number;
	localRate: number;
	importRate: number;
	totalLocalRate: number;
	totalImportRate: number;
	subTotal: number;
	taxTotal: number;

	constructor(
		grandTotal: number,
		localRate: number,
		importRate: number,
		totalLocalRate: number,
		totalImportRate: number,
		subTotal: number,
		taxTotal: number
	) {
		this.grandTotal = grandTotal;
		this.localRate = localRate;
		this.importRate = importRate;
		this.totalLocalRate = totalLocalRate;
		this.totalImportRate = totalImportRate;
		this.subTotal = subTotal;
		this.taxTotal = taxTotal;
	}
}
